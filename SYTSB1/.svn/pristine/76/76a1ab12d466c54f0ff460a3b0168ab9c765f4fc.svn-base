package demo.ws.cxf.sec;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.ws.security.WSPasswordCallback;

public class ClientPasswordCallback implements CallbackHandler {

	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		for (int i = 0; i < callbacks.length; i++) {
			WSPasswordCallback pc = (WSPasswordCallback) callbacks[i];
			System.out.println("identifier: " + pc.getIdentifier());
			// 这里必须设置密码，否则会抛出：java.lang.IllegalArgumentException: pwd == null
			// but a password is needed
			pc.setPassword("testPassword33");// ▲【这里必须设置密码】▲
		}
	}
}