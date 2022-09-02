package designPatterns.behavioralDesignPattern.strategyDesignPattern;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Strategy {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the first value: ");
        float value1=Float.parseFloat(br.readLine());
        System.out.print("Enter the second value: ");
        float value2=Float.parseFloat(br.readLine());
        Query query = new Query(new Add());
        System.out.println("Addition = " + query.executeStrategy(value1, value2));
        query = new Query(new Sub());
        System.out.println("Subtraction = " + query.executeStrategy(value1, value2));
        query = new Query(new Mul());
        System.out.println("Multiplication = " + query.executeStrategy(value1, value2));
        query = new Query(new Div());
        System.out.println("Multiplication = " + query.executeStrategy(value1, value2));
        query = new Query(new Mod());
        System.out.println("Multiplication = " + query.executeStrategy(value1, value2));
    }



}
