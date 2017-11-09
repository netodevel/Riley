package br.com.rileyframework.commands.service;

import java.util.ArrayList;
import java.util.List;

public abstract class Command implements Observable {
	
	private List<Observer> observers = new ArrayList<Observer>();
	private List<ObserverFail> observersFail = new ArrayList<ObserverFail>();

	public void onSuccess(Observer observer) {
		observers.add(observer);
	}
	
	public void onFailed(ObserverFail observer) {
		observersFail.add(observer);
	}
	
	public void broadcastSuccess() {
		for (Observer ob : this.observers) {
	        ob.call();
	    }
	}
	
	public void broadcastSuccess(Object... params) {
		for (Observer ob : this.observers) {
	        ob.call(params);
	    }
	}
	
	public void broadcastFailed() {
		for (ObserverFail ob : this.observersFail) {
	        ob.call();
	    }
	}
	
}
