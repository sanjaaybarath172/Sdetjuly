package com.selenium.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Pract1 {
    public static void main(String[] args) {
         int val=12345;
         int rev=0;
         while(val!=0)
         {
             int digit=val%10;
             rev=rev*10 +digit;
             val=val/10;
         }
        System.out.println(rev);
        }
    }

