package com.github.georgovassilis.gmps.ui;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.ReflectionUtils;

import com.bazarooma.flatbus.shared.BusListener;
import com.github.georgovassilis.gmps.client.events.Bus;
import com.github.georgovassilis.gmps.common.domain.AddressDto;
import com.github.georgovassilis.gmps.common.domain.ContactDto;
import com.github.georgovassilis.gmps.common.domain.PersonalDetailsDto;

public class MockBus implements Bus {

	private List<BusListener> listeners = new ArrayList<BusListener>();

	@Override
	public void register(BusListener listener) {
		listeners.add(listener);
	}

	@Override
	public void unregister(BusListener listener) {
		throw new RuntimeException("not implemented");
	}

	@Override
	public <T> List<T> all(T object) {
		throw new RuntimeException("not implemented");
	}

	@Override
	public <T> List<T> allWithValues(T object) {
		throw new RuntimeException("not implemented");
	}

	@Override
	public <T> T firstValue(T object) {
		throw new RuntimeException("not implemented");
	}

	@Override
	public void cancelCurrentEvent() {
		throw new RuntimeException("not implemented");
	}

	@Override
	public void onAddressUpdated(AddressDto address) {
		throw new RuntimeException("not implemented");
	}

	@Override
	public void onAppStarted() {
		throw new RuntimeException("not implemented");
	}

	@Override
	public void onContactListsUpdated(List<PersonalDetailsDto> contacts) {
		invokeIfExists("onContactListsUpdated", new Class[]{List.class}, new Object[]{contacts});
	}

	@Override
	public void onContactUpdated(ContactDto contact) {
		invokeIfExists("onContactUpdated", new Class[] { ContactDto.class },
				new Object[] { contact });
	}

	private Object invokeIfExists(String methodName, Class[] argumentTypes,
			Object[] arguments) {
		Object result = null;
		for (BusListener listener : listeners) {
			Method m = ReflectionUtils.findMethod(listener.getClass(),
					methodName, argumentTypes);
			if (m != null) {
				try {
					Object o = m.invoke(listener, arguments);
					if (o!=null)
						result = o;
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
		return result;
	}
}
