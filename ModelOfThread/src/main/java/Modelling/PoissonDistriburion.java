package Modelling;

import java.util.ArrayList;

import static java.lang.Math.pow;

public class PoissonDistriburion {
    ArrayList<Integer> a = new ArrayList<Integer>();
    ArrayList<Range> intervals = new ArrayList<Range>(); //промежутки вероятностей
    ArrayList<Double> p = new ArrayList<Double>();
    double u;
    double t;
    double lambda;
    public PoissonDistriburion( double lambda, double t) {
        setU();
        setA();
        setT(t);
        setLambda(lambda);
        setP();
        setIntervals();
    }
    public ArrayList<Integer> getA() {
        return a;
    }
    public void setA() {
        for(int i = 0; i < 36; i++){
            a.add(i);
        }
    }
    public ArrayList<Range> getIntervals() {
        return intervals;
    }
    public void setIntervals() {
        intervals = new ArrayList<Range>(a.size());
        Range range = new Range(0,p.get(0));
        intervals.add(range);
        for(int i = 1; i<a.size(); i++){
            intervals.add(new Range(intervals.get(i-1).getRight(),intervals.get(i-1).getRight()+p.get(i)));
        }
    }
    public ArrayList<Double> getP() {
        return p;
    }
    public void setP() {
        p.add(0,pow(Math.E, -lambda*t));
        for(int i = 1; i< a.size();i++){
            p.add(i,lambda*t/i*p.get(i-1));
        }
    }
    public double getU() {
        return u;
    }
    public void setU() {
        this.u = Math.random();
    }
    public double getT() {
        return t;
    }
    public void setT(double t) {
        this.t = t;
    }
    public double getLambda() {
        return lambda;
    }
    public void setLambda(double lambda) {
        this.lambda = lambda;
    }
    public int returnNum(double u, ArrayList<Range> ranges){
        for(int i = 0; i < ranges.size(); i++){
            if(u>ranges.get(i).getLeft() && u<=ranges.get(i).getRight()) { //равенство с 1 из сторон!!!!
                return  a.get(i);
            }
        }
        return 100;
    }
    public static void main(String[] args) {

        PoissonDistriburion pd = new PoissonDistriburion(1,10);

        System.out.println("Сгенерированное число из [0,1]: " + pd.getU()); //эти данные двыводились для дебага
        System.out.println("Лямбда: "+pd.getLambda());
        System.out.println("Временной промежуток: " +pd.getT());
        System.out.println("А: " + pd.getA()); //это какие числа могут быть выведены в числе заявок
        System.out.println("P: " + pd.getP()); //это вероятности к числам из А
        System.out.println("Интервалы: " + pd.getIntervals()); //это я делила для своего диплома, тк у меня параметр лямбда - функция от t

        System.out.println("Число заявок: " + pd.returnNum(pd.u, pd.intervals)); //это число заявок сгенерированное за 10 секунд простейщим потоком с параметром = 1
        //такая домашка была точно у ПМИ на год старше нас, по крайней мере Машенька давала такую задачу выпуску 2018
        //ну и в цикле надо будет сделать в зависимости от промежутка времени моделирование приходящих заявок
        //у меня было разбиение интервала на промежутки длины 1



    }
}
