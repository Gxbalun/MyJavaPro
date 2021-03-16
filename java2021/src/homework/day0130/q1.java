package homework.day0130;

public class q1 {
    static int n = 5;
    static int s = 1;
    static int [][]a = new int[n][n];
    public static void printhxjz(int l,int r){
        //上
        for (int i = l; i <= r; i++) {
            a[l][i] = s;
            s++;
        }
        //右
        for (int i = l+1; i <= r ; i++) {
            a[i][r] = s;
            s++;
        }
        //下
        for (int i = r-1; i >= l; i--) {
            a[r][i] = s;
            s++;
        }
        //右
        for (int i = r-1; i > l; i--) {
            a[i][l] = s;
            s++;
        }
        if (s == n*n+1) {
            return;
        }
        printhxjz(l+1,r-1);
    }

    public static void main(String[] args) {
        printhxjz(0,n-1);
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] < 10){
                    System.out.print("0"+a[i][j]+" ");
                }else{
                    System.out.print(a[i][j]+" ");
                }
            }
            System.out.println();
        }
    }
}
