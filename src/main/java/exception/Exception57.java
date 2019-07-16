package exception;

import org.junit.Test;

/**
 * @author fatiaoyezi
 * @title: Exception57
 * @projectName effective-java-example
 * @description: 只针对异常的情况才使用异常
 * @date 2019/7/12 14:55
 */
public class Exception57 {



    /**
     * 异常模式遍历一千条数据所需时间
     */
    @Test
    public void test1() {
        int[] t1= new int[1000];
        for (int i = 0; i < 1000; i++) {
            t1[i] = i;
        }

        long start = System.currentTimeMillis();
        try{
            int i=0;
            while(true){
                System.out.println(t1[i]);
                i++;
            }
        }catch(ArrayIndexOutOfBoundsException e){

        }

        System.out.println(System.currentTimeMillis() - start);

    }


    /**
     * 正常模式处理1000条数据所需时间
     */
    @Test
    public void test2(){
        int[] t1= new int[1000];
        for (int i = 0; i < 1000; i++) {
            t1[i] = i;
        }

        long start = System.currentTimeMillis();

        for (int i : t1) {
            System.out.println(i);
        }

        System.out.println(System.currentTimeMillis() - start);

    }


    /**
     * 当使用异常模式时，产生了与这个bug相关的异常就会被捕捉到，并且被错误的解释为正常的循环终止条件。
     */
    @Test
    public void test3(){
        String[] t1={"11","22"};
        String[] t2 = {};

        try{
            int i=0;
            while(true){
                System.out.println(t1[i]);
                System.out.println(t2[0]);
                i++;
            }
        }catch(ArrayIndexOutOfBoundsException e){
        }
    }


}
