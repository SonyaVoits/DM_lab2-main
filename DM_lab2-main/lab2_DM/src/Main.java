import java.io.BufferedReader;
import java.io.FileReader;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static  int sum_edges_vertexes ( int[][] con_matr){
        int sum = 0;
        for (int i = 0; i < con_matr.length; i++) {
            for (int j = 0; j < con_matr.length; j++) {
                sum += con_matr[i][j];
            }
        }
        return sum;
    }

    public static  int euler ( int matr[][],int con_matr_[][]) {
        int N = con_matr_.length;
        int[][] con_matr= new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                con_matr[i][j] = con_matr_[i][j];
            }
        }
        int[] p = new int [100001];
        int p1;
        int p2;
        int s = 0;
        int k = sum_edges_vertexes(con_matr) / 2;
        int weight = 0;

        p1 = 0;
        p2 = k + 1;
        p[p1] = s;

        while (p1 >= 0) {
            int i, v = p[p1];
            for (i = 0; i < N; ) {
                if (con_matr[v][i] != 0)
                {
                    con_matr[v][i] = con_matr[v][i] - 1;
                    con_matr[i][v] = con_matr[i][v] - 1;
                    p[++p1] = i;
                    v = i;
                    i = 0;
                } else {
                    i++;
                }
            }
            if (i >= N) {
                p[--p2] = p[p1--];
            }
        }

        if (p2 > 0) {
            System.out.println("ãðàô íå Åéëåðîâèé");
        } else {
            System.out.println(" Шлях - Вартість");
            for (int i = 0; i < k; i++) {
                System.out.println("("+ (p[i] + 1) + " ; "+ (p[i + 1] + 1)+") - "+ matr[p[i]][p[i + 1]] );
                weight += matr[p[i]][p[i + 1]];
            }
            System.out.println("\n Вартість шляху: " + weight);
        }

        return 0;
    }


    public static int[][] connectivity_matrix (int matr[][]){
        int N = matr.length;
        int[][] con_matr = new int[N][N];
        System.out.println("\nМатриця зв'язності: " );
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (matr[i][j] > 0) {
                    con_matr[i][j] = 1;
                } else {
                    con_matr[i][j] = matr[i][j];
                }
                System.out.print(con_matr[i][j] + "\t");
            }
            System.out.println();
        }

        return con_matr;
    }

    public static  int add_edges ( int con_matr[][],int vert_degr[],int matr[][]){
        int N = con_matr.length;
        for (int i = 0; i < N; i++) {
            if (vert_degr[i] % 2 != 0) {
                for (int j = 0; j < N; j++) {
                    if (vert_degr[j] % 2 != 0 && con_matr[i][j] != 0) {
                        con_matr[i][j] = con_matr[i][j] + 1;
                    }
                }
            }
        }

        System.out.println("\nОновлена матрця зв'язності: " );
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(con_matr[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();

        euler(matr, con_matr);

        return 0;
    }


    public static  int if_euler ( int con_matr[][],int vert_degr[],int matr[][]){
        int N = con_matr.length;
        for (int i = 0; i < N; i++) {
            if (vert_degr[i] != 0) {
                add_edges(con_matr, vert_degr, matr);
                break;
            } else {
                euler(matr, con_matr);
            }
        }

        return 0;
    }

    public static int vertex_degree ( int con_matr[][],int matr[][]){
        int N = con_matr.length;
        int[] vert_degr = new int[N];
        System.out.println();
        System.out.print( "Вершини ");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                vert_degr[i] += con_matr[i][j];
            }
            if (vert_degr[i] % 2 != 0) {
                System.out.print( (i + 1) + " ");
            }
        }
        System.out.print( "непарні.\n");

        if_euler(con_matr, vert_degr, matr);

        return 0;
    }

    public static void main(String[] args) throws Exception {
        int N = 8;
        int K = 100000;

        Scanner sc = new Scanner(new BufferedReader(new FileReader("C:\\Users\\User\\Desktop\\4 курс\\ІІ\\Дискретні моделі в САПР\\ЛР\\l2-1.txt")));
        int r = 8;
        int[][] matrix = new int[r][r];
        int[][] matrix2 = new int[r][r];
        while (sc.hasNextLine()) {
            for (int i = 0; i < matrix.length; i++) {
                String[] line = sc.nextLine().trim().split(" ");
                for (int j = 0; j < line.length; j++) {
                    matrix[i][j] = Integer.parseInt(line[j]);
                    matrix2[i][j] = Integer.parseInt(line[j]);
                }

            }
        }
        System.out.println("Матриця інцидентності з файлу:");
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < r; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        int con_matr[][];

        System.out.println( "\nКількість заданих вершин: " + N );
        con_matr = connectivity_matrix(matrix);
        System.out.println("\n\nПошук оптимального шляху...\n");
        vertex_degree(con_matr, matrix);

    }
}

