/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ftd.educational.mvc.utils;

/**
 *
 * @author Cyber
 */
public class util {

    public static final boolean tryParseLong(String value) {
        boolean isLong = false;
        try {
            Long.parseLong(value);
            isLong = true;
        } finally {
            return isLong;
        }
    }

}
