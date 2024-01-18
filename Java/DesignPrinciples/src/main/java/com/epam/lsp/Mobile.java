package com.epam.lsp;

public interface Mobile extends Ipod{
	public abstract void sendSMS();
	public abstract void call();
	public abstract void playVideo(String videoFileName);

}
