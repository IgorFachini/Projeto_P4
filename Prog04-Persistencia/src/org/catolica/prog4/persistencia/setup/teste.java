/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.catolica.prog4.persistencia.setup;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author igor.fachini
 */
public class teste {
     
   // public static void main(String[] args) {
//        int[] n1 = new int[8];
//        int[] n2 = new int[8];
//        for (int i = 0; i < n1.length; i++) {
//          n1[i] = Integer.parseInt(JOptionPane.showInputDialog("n1 posição: " + i ));
//        }
//        for (int i = 0; i < n1.length; i++) {
//          n2[i] = Integer.parseInt(JOptionPane.showInputDialog("n2 posição: " + i ));
//        }
//        somaBinario(n1, n2);

    public static void somaBinario(int[] vetor1, int[] vetor2) {
        int soma;
        int[] result = new int[8];
        for (int i = 0; i < vetor1.length; i++) {
            if ((soma = (vetor1[i] + vetor2[i])) == 2) {
                soma = 1;
            }
            result[i] = soma;
        }
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i]);
        }
    }

    public static void diminiuiBinario(int[] vetor1, int[] vetor2) {
        int soma;
        int[] result = new int[8];
        for (int i = 0; i < vetor1.length; i++) {
                if ((soma = (vetor1[i] - vetor2[i])) == 1) {
                    soma = 1;
                }
                result[i] = soma;
        }
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i]);
        }
    }
    
    public static void divideBinario(int[] vetor1, int[] vetor2) {
        int soma;
        int[] result = new int[8];
        for (int i = 0; i < vetor1.length; i++) {
                if ((soma = (vetor1[i] * vetor2[i])) == 1) {
                    soma = 1;
                }
                result[i] = soma;
        }
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i]);
        }
    }

    public static void multiplicaBinario(int[] vetor1, int[] vetor2) {
        int soma;
        int[] result = new int[8];
        for (int i = 0; i < vetor1.length; i++) {
            if ((soma = (vetor1[i] * vetor2[i])) == 1) {
                soma = 1;
            }
            result[i] = soma;
        }
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i]);
        }
    }

    public static void main(String[] args) {
        int[] n1 = new int[8];
        int[] n2 = new int[8];
        for (int i = 0; i < n1.length; i++) {
            n1[i] = Integer.parseInt(JOptionPane.showInputDialog("n1 posição: " + i));
        }
        for (int i = 0; i < n1.length; i++) {
            n2[i] = Integer.parseInt(JOptionPane.showInputDialog("n2 posição: " + i));
        }
        divideBinario(n1, n2);

    }
}
    //}
//}

