package com.wspn.web;
/*
 * Copyright (c) 2010-2018 Nathan Rajlich
 *
 *  Permission is hereby granted, free of charge, to any person
 *  obtaining a copy of this software and associated documentation
 *  files (the "Software"), to deal in the Software without
 *  restriction, including without limitation the rights to use,
 *  copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the
 *  Software is furnished to do so, subject to the following
 *  conditions:
 *
 *  The above copyright notice and this permission notice shall be
 *  included in all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 *  OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 *  HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 *  WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 *  FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 *  OTHER DEALINGS IN THE SOFTWARE.
 */

import java.lang.reflect.Executable;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Time;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


import org.eclipse.jetty.util.thread.ScheduledExecutorScheduler;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;

/** This example demonstrates how to create a websocket connection to a server. Only the most important callbacks are overloaded. */
public class ExampleClient extends WebSocketClient {

	class MyThread {
		public void run() throws InterruptedException {
//			while (true) {
//				Thread.sleep(500);
//				send("{\"message\": \"config_get\"}");
//				Thread.sleep(500);
//			}
		}
	}
	
	public ExampleClient( URI serverUri , Draft draft ) {
		super( serverUri, draft );
	}

	public ExampleClient( URI serverURI ) {
		super( serverURI );
	}

	public ExampleClient( URI serverUri, Map<String, String> httpHeaders ) {
		super(serverUri, httpHeaders);
	}

	@Override
	public void onOpen( ServerHandshake handshakedata ) {
		System.out.println( "opened connection" );
		TimerTask task=new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				send("{\"message\": \"ue_get\",\"stats\":true}");
			}
		};
		Timer timer=new Timer();
		long delay=0;
		long inteval=100;
		timer.schedule(task, delay,inteval);
		// if you plan to refuse connection based on ip or httpfields overload: onWebsocketHandshakeReceivedAsClient
	}

	@Override
	public void onMessage( String message ) {
		System.out.println( "received: " + message );
		JSONObject jsonObject = new JSONObject(message);
		System.err.println();
		
	}

	@Override
	public void onClose( int code, String reason, boolean remote ) {
		// The codecodes are documented in class org.java_websocket.framing.CloseFrame
		System.out.println( "Connection closed by " + ( remote ? "remote peer" : "us" ) + " Code: " + code + " Reason: " + reason );
	}

	@Override
	public void onError( Exception ex ) {
		ex.printStackTrace();
		// if the error is fatal then onClose will be called additionally
	}

	public static void main( String[] args ) throws URISyntaxException {
		Map<String, String> httpHeaders=new HashMap<>();
		httpHeaders.put("Origin", "chrome-extension://omalebghpgejjiaoknljcfmglgbpocdp");
		ExampleClient c = new ExampleClient( new URI( "ws://10.108.145.150:9001" ),httpHeaders); // more about drafts here: http://github.com/TooTallNate/Java-WebSocket/wiki/Drafts
		c.connect();
//		while (!c.getReadyState().equals(READYSTATE.OPEN)) {
//		}
		
	}

}