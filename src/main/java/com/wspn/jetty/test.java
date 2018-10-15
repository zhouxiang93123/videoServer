package com.wspn.jetty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class test {
	public static void main(String args[]) {
		Process process = null;
		try {
			process = Runtime.getRuntime().exec("wondershaper eth2 1000000 15000");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
