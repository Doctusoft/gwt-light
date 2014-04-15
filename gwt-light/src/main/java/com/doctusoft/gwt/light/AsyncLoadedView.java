package com.doctusoft.gwt.light;

/**
 * Deprecated. The preferred way is the proper MVP architecture using Bindings 
 */
@Deprecated
public interface AsyncLoadedView {
	
	@Deprecated
	void afterViewLoaded(ViewLoadedListener listener);
	
	public interface ViewLoadedListener {
		void viewLoaded(AsyncLoadedView view);
	}

}
