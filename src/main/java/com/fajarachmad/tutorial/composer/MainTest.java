package com.fajarachmad.tutorial.composer;

import java.text.DecimalFormat;

public class MainTest {

	public static void main(String[] args) {
		
		double dbl = new Double("5645.564454");
		System.out.println(new DecimalFormat("###,##0.00").format(dbl));

	}

}
