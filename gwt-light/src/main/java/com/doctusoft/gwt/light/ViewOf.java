package com.doctusoft.gwt.light;

import com.google.gwt.user.client.ui.IsWidget;

public interface ViewOf<Presenter> extends IsWidget {

	void setPresenter(Presenter presenter);

	void viewPresented();
}