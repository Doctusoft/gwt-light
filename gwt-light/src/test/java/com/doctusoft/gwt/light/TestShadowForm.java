package com.doctusoft.gwt.light;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.bean.binding.observable.ObservableValueBinding;

public class TestShadowForm {
	
	@Test
	public void testFormCommit() {
		ExampleFormVO vo = new ExampleFormVO();
		vo.setValue("helloworld");
		ShadowForm form = new ShadowForm();
		ValueBinding<String> wrappedBinding = form.wrap(Bindings.on(vo).get(ExampleFormVO_._value));
		assertEquals("helloworld", vo.getValue());
		// now we act like a text field that changes the value
		wrappedBinding.setValue("changed");
		// the value in the VO however remains the same
		assertEquals("helloworld", vo.getValue());
		// but the binding itself returns the shadow value for the form element
		assertEquals("changed", wrappedBinding.getValue());
		// after committed
		form.commit();
		// the changed value is present in the model
		assertEquals("changed", vo.getValue());
	}
	
	@Test
	public void testFormCancel() {
		ExampleFormVO vo = new ExampleFormVO();
		vo.setValue("helloworld");
		ShadowForm form = new ShadowForm();
		ValueBinding<String> wrappedBinding = form.wrap(Bindings.on(vo).get(ExampleFormVO_._value));
		assertEquals("helloworld", vo.getValue());
		// the user enters a value
		wrappedBinding.setValue("changed");
		// but cancels the form
		form.cancel();
		// so the old value remains both in the model
		assertEquals("helloworld", vo.getValue());
		// and in the binding
		assertEquals("helloworld", wrappedBinding.getValue());
	}

	@Test
	public void testObservableSimpleFormCommit() {
		ExampleFormVO vo = new ExampleFormVO();
		vo.setValue("helloworld");
		ShadowForm form = new ShadowForm();
		ObservableValueBinding<String> wrappedBinding = form.wrap(Bindings.obs(vo).get(ExampleFormVO_._value));
		assertEquals("helloworld", vo.getValue());
		// now we act like a text field that changes the value
		wrappedBinding.setValue("changed");
		// the value in the VO however remains the same
		assertEquals("helloworld", vo.getValue());
		// but the binding itself returns the shadow value for the form element
		assertEquals("changed", wrappedBinding.getValue());
		// after committed
		form.commit();
		// the changed value is present in the model
		assertEquals("changed", vo.getValue());
	}

	@Test
	public void testObservableSimpleFormCancel() {
		ExampleFormVO vo = new ExampleFormVO();
		vo.setValue("helloworld");
		ShadowForm form = new ShadowForm();
		ObservableValueBinding<String> wrappedBinding = form.wrap(Bindings.obs(vo).get(ExampleFormVO_._value));
		assertEquals("helloworld", vo.getValue());
		// now we act like a text field that changes the value
		wrappedBinding.setValue("changed");
		// but we cancel the form
		form.cancel();
		// so the model and the binding is reverted
		assertEquals("helloworld", vo.getValue());
		assertEquals("helloworld", wrappedBinding.getValue());
	}

	@Test
	public void testModelChangeInObservableBinding() {
		ExampleFormVO vo = new ExampleFormVO();
		vo.setValue("helloworld");
		ShadowForm form = new ShadowForm();
		ObservableValueBinding<String> wrappedBinding = form.wrap(Bindings.obs(vo).get(ExampleFormVO_._value));
		assertEquals("helloworld", vo.getValue());
		// now we act like a text field that changes the value
		wrappedBinding.setValue("changed");
		// the value in the VO however remains the same
		assertEquals("helloworld", vo.getValue());
		// now the model itself changes
		vo.setValue("changedmodel");
		// now the form should have cancelled it's shadow value "changed", so the commit is effectless
		form.commit();
		assertEquals("changedmodel", vo.getValue());
		// but if now the user enters a value again
		wrappedBinding.setValue("yetanothervalue");
		form.commit();
		// it will be written to the model
		assertEquals("yetanothervalue", vo.getValue());
	}

	@Test
	public void testModelChangeInObservableBinding2() {
		ExampleFormVO vo = new ExampleFormVO();
		vo.setValue("helloworld");
		ShadowForm form = new ShadowForm();
		ObservableValueBinding<String> wrappedBinding = form.wrap(Bindings.obs(vo).get(ExampleFormVO_._value));
		assertEquals("helloworld", vo.getValue());
		// now we act like a text field that changes the value
		wrappedBinding.setValue("changed");
		// the value in the VO however remains the same
		assertEquals("helloworld", vo.getValue());
		// now the model itself changes
		vo.setValue("changedmodel");
		// the shadow value is erased from the wrapper, and the model value is returned
		assertEquals("changedmodel", wrappedBinding.getValue());
	}
}
