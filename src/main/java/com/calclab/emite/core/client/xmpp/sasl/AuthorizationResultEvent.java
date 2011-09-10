/*
 * ((e)) emite: A pure Google Web Toolkit XMPP library
 * Copyright (c) 2008-2011 The Emite development team
 * 
 * This file is part of Emite.
 *
 * Emite is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 *
 * Emite is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with Emite.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.calclab.emite.core.client.xmpp.sasl;

import com.calclab.emite.core.client.xmpp.session.Credentials;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.google.web.bindery.event.shared.Event;

public class AuthorizationResultEvent extends Event<AuthorizationResultEvent.Handler> {
	
	public interface Handler {
		void onAuthorizationResult(AuthorizationResultEvent event);
	}
	
	public static final Type<Handler> TYPE = new Type<Handler>();

	private final Credentials credentials;
	private final boolean success;

	/**
	 * Build a failed authorization event
	 */
	public AuthorizationResultEvent() {
		this(null, false);
	}

	/**
	 * Build a succeeded authorization event with the current credentials
	 * 
	 * @param uri
	 *            the uri of the authorized user
	 */
	public AuthorizationResultEvent(final Credentials credentials) {
		this(credentials, true);
	}

	private AuthorizationResultEvent(final Credentials credentials, final boolean success) {
		this.credentials = credentials;
		this.success = success;
	}
	
	public Credentials getCredentials() {
		return credentials;
	}

	public XmppURI getXmppUri() {
		return credentials.getXmppUri();
	}

	public boolean isSuccess() {
		return success;
	}

	@Override
	public Type<Handler> getAssociatedType() {
		return TYPE;
	}

	@Override
	public String toDebugString() {
		final String value = success ? " Success - " + credentials.getXmppUri() : " Failed!";
		return super.toDebugString() + value;
	}

	@Override
	protected void dispatch(final Handler handler) {
		handler.onAuthorizationResult(this);
	}

}
