/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkipncode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author spidchenko.d
 */
public class CheckIPNCode {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

    do{
        System.out.println("Введите 10 цифр ИНН чтобы проверить его правильность (Введите -1 чтобы завершить работу):");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long inn=0;
        try {
            inn = Long.parseLong(br.readLine()); //9210000059156
        } catch (IOException ex) {
            System.out.println("IOException!");
        }
        catch (NumberFormatException ex) {
            System.out.println("Неверный номер!");
            continue;
        }

    if (inn == -1) break;
 
        //Вычисляем контрольную цифру:
        //Из Википедии:
        //вважатимемо, що код має вигляд АБВГҐДЕЄЖЗ
        //Розраховуємо контрольну суму Х = А*(-1) + Б*5 + В*7 + Г*9 + Ґ*4 + Д*6 + Е*10 + Є*5 + Ж*7
        long checkDigit = 0;
        checkDigit = inn/1000000000L%10 * -1;       //А*(-1)
        checkDigit+= inn/100000000L%10 * 5;         //Б*5
        checkDigit+= inn/10000000L%10 * 7;          //В*7
        checkDigit+= inn/1000000L%10 * 9;           //Г*9
        checkDigit+= inn/100000L%10 * 4;            //Ґ*4
        checkDigit+= inn/10000L%10 * 6;             //Д*6
        checkDigit+= inn/1000L%10 * 10;             //Е*10
        checkDigit+= inn/100L%10 * 5;               //Є*5
        checkDigit+= inn/10L%10 * 7;                //Ж*7
        //залишок від ділення контрольної суми на одинадцять буде контрольним числом
        checkDigit%=11;
        
        if (checkDigit == 10) checkDigit=0;         //Костыль
        
        System.out.println(checkDigit);
    }while (true);  //Бесконечный цикл, выход по вводу "-1"
    }   
}