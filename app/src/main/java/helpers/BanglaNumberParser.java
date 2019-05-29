/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package helpers;

/**
 * Created by rhythmshahriar on 9/14/17.
 */

public class BanglaNumberParser {

    public static String getBangla (String englishInput)
    {
        String converted = "";
        char temp='0';
        for (int i =0; i< englishInput.length();i++)
        {

            temp = getBanglaDigit(englishInput.charAt(i));
            converted+=String.valueOf(temp);

        }
        return converted;
    }

    public static String getEnglish (String banglaInput)
    {
        String converted = "";
        char temp='0';
        for (int i =0; i< banglaInput.length();i++)
        {

            temp = getEnglishDigit(banglaInput.charAt(i));
            converted+=String.valueOf(temp);

        }
        return converted;
    }


    private static char getBanglaDigit (char engDigit)
    {
        char digit = engDigit;
        switch (engDigit)
        {
            case '0':
                digit = '০';
            break;
            case '1':
                digit = '১';
                break;
            case '2':
                digit = '২';
                break;
            case '3':
                digit = '৩';
                break;
            case '4':
                digit = '৪';
                break;
            case '5':
                digit = '৫';
                break;
            case '6':
                digit = '৬';
                break;
            case '7':
                digit = '৭';
                break;
            case '8':
                digit = '৮';
                break;
            case '9':
                digit = '৯';
                break;
            default:
                digit = engDigit;
                break;




        }
        return digit;
    }

    private static char getEnglishDigit (char bangDigit)
    {
        char digit = bangDigit;
        switch (bangDigit)
        {
            case '০':
                digit = '0';
                break;
            case '১':
                digit = '1';
                break;
            case '২':
                digit = '2';
                break;
            case '৩':
                digit = '3';
                break;
            case '৪':
                digit = '4';
                break;
            case '৫':
                digit = '5';
                break;
            case '৬':
                digit = '6';
                break;
            case '৭':
                digit = '7';
                break;
            case '৮':
                digit = '8';
                break;
            case '৯':
                digit = '9';
                break;
            default:
                digit = bangDigit;
                break;




        }
        return digit;
    }
}
