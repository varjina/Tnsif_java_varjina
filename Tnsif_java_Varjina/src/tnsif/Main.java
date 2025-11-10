package com.tnsif;
import java.util.Scanner;
import java.text.DecimalFormat;

public class Main {
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
System.out.println("Enter your Choise:");
DecimalFormat df = new DecimalFormat("0.00");
int choice = sc.nextInt();
System.out.println("Enter your hours:");
int hours = sc.nextInt();
System.out.println("Enter your cost:");
double costPerHour = sc.nextDouble();

	        Airfares airfare = null;

	        switch(choice) {
	            case 1:
	                airfare = new AirIndia(hours,costPerHour);
	                break;
	            case 2:
	                airfare = new KingFisher(hours, costPerHour);
	                break;
	            case 3:
	                airfare = new Indigo(hours, costPerHour);
	                break;
	            default:
	                System.out.println("Invalid choice");
	                System.exit(0);
	        }

	        double amount = airfare.calculateAmount();
	        System.out.println(df.format(amount));
	    }
	}



