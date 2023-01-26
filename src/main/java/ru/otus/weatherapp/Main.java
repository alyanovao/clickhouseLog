package ru.otus.weatherapp;

import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;

@Slf4j
public class Main {

	public static void main(String[] args) throws SQLException {
		System.out.println("Hello world");
		log.info("Test");
		Demo demo = new Demo();
		DemoHikari demo1 = new DemoHikari();
	}
}