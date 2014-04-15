package com.doctusoft.gwt.light;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.google.common.collect.Lists;
import com.google.gwt.dom.client.Element;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.i18n.client.ConstantsWithLookup;
import com.xedge.jquery.client.JQuery;
import com.xedge.jquery.client.handlers.IterateElementHandler;

public abstract class AbstractLightPanel<Actual extends AbstractLightPanel<Actual>> extends AbstractLightWidget<Actual> implements AsyncLoadedView {
	
	protected boolean viewLoaded = false;
	
	Logger logger = Logger.getLogger(AbstractLightPanel.class.getName());
	
	protected List<ViewLoadedListener> viewLoadListeners = new ArrayList<ViewLoadedListener>();
	
	protected static long probeIdCounter = 0;

	private JQuery loadingIndicator;
	
	public AbstractLightPanel(final JQuery selector) {
		super(selector);
	}
	public AbstractLightPanel(final String selector) {
		this(JQuery.select(selector));
	}
	
	protected abstract ConstantsWithLookup getMessages();

	protected void stringTemplate(final String template) {
		JQuery content = JQuery.select(template);
		content.appendTo(root);
		fireOnTemplateLoaded();
		fireViewLoadedListeners();
	}
	
	protected void remoteTemplate(final String templateUrl, boolean isLoading) {
		logger.info("loading template: " + templateUrl);
		// TODO: cache loaded templates
		final RequestBuilder req = new RequestBuilder(RequestBuilder.GET, templateUrl + "?" + new Date().getTime());
		req.setHeader("Accept", "text/html; charset=utf-8");
		if (isLoading) {
			loadingIndicator = JQuery.select("<div class=\"loading\"> </div>");
			root.append(loadingIndicator);
		}
		try {
			req.sendRequest(null, new RequestCallback() {
				@Override
				public void onResponseReceived(final Request request,
						final Response response) {
					String html = response.getText();
					root.append(JQuery.select(html));
					applyTranslations();
					postProcessTemplate();
					fireOnTemplateLoaded();
					fireViewLoadedListeners();
					if (loadingIndicator != null) {
						loadingIndicator.remove();
						loadingIndicator = null;
					}
				}

				@Override
				public void onError(final Request request, final Throwable exception) {
					// FIXME handle error
				}
			});
		} catch (final RequestException e) {
			// FIXME handle error
		}
	}
	
	protected void postProcessTemplate() {
		// default implementation does nothing
	}
	
	protected void applyTranslations() {
		// content.find() should work, but it doesn't for some reason ...
		final ConstantsWithLookup messages = getMessages();
		root.find("[translation-key]").each(new IterateElementHandler() {
			@Override
			public void execute(int index, Element element, JQuery currentJQuery) {
				final JQuery _this = JQuery.select(element);
				final String key = _this.attr("translation-key");
				final String methodName = key.replace('.', '_');
				String msg = null;
				try {
					msg = messages.getString(methodName);
				} catch (Exception e) {
					msg = "not found: " + key; 
				}
				_this.html(msg);
			}
		});
	}
	
	protected void fireOnTemplateLoaded() {
		// parse i18n stuff
		onTemplateLoaded();
	}

	protected abstract void onTemplateLoaded();
	
	@Override
	public void afterViewLoaded(ViewLoadedListener listener) {
		if (!viewLoaded) {
			viewLoadListeners.add(listener);
		} else {
			listener.viewLoaded(this);
		}
	}
	
	protected void fireViewLoadedListeners() {
		fireViewLoadedListenersInner();
	}

	/**
	 * Use if the usual place when the event gets fired is not good.
	 */
	protected void fireViewLoadedListenersInner() {
		// wrapping to avoid concurrentmodificationexceptions
		for (ViewLoadedListener listener : Lists.newArrayList(viewLoadListeners)) {
			listener.viewLoaded(this);
		}
		viewLoaded = true;
	}
}
