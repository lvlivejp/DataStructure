package com.lvlivejp.datastructure;

import org.apache.commons.lang3.StringUtils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StackByArray<E> {

    private Object[] objects;

    @Override
    public String toString() {
        return "StackByArray{" +
                "objects=" + Arrays.toString(objects) +
                '}';
    }

    transient private int top = -1; //最上层有数据的index

    public StackByArray(int size) {
        objects = new Object[size];
    }

    public boolean push(E e){
        resize();
        objects[++top]=e;
        return true;
    }

    private void resize() {
        if(top+1==objects.length){
            objects = Arrays.copyOf(objects,objects.length*2);
        }
    }

    public E pop(){
        if(top == -1){
            return null;
        }
        E e = (E) objects[top];
        objects[top]=null;
        top--;
        return e;
    }

    public E peek(){
        if(top == -1){
            return null;
        }
        return (E) objects[top];
    }
    public static void main(String[] args) throws ScriptException {
        StackByArray<Character> stackByArray = new StackByArray<>(10);
        String temp ;
        temp = "1+2+2*((3+5*2)+2)/5*2*3*4*6/3*2*3*(4/2+3)+3*2";
//      temp = "6/3*2+1+2";
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName( "JavaScript" );
        System.out.println("JavaScript:"+engine.eval(temp));
        String num="";
        List<String> rpn = new ArrayList<String>();
        char[] chars = temp.toCharArray();
        for (char a : chars) {
            // 如果是数字，就继续下一次循环，拼接两位数以上的数字
            if(a>=48 && a<=57){
                num+=a;
                continue;
            }
            //如果数字变量不为空，则放入List中
            if(StringUtils.isNoneBlank(num)){
                rpn.add(num);
                System.out.println(num);
                num="";
            }

            if(a=='('){
                //左括号直接入栈
                stackByArray.push(a);
            }else if(a==')'){
                //右括号时，将所有操作符出栈，直到遇到左括号
                Character c;
                while((c=stackByArray.pop()) != '('){
                    rpn.add(String.valueOf(c));
                    System.out.println(c);
                }
            }else if(a=='*' || a=='/'){
                //乘法除法时，将栈顶的乘法和除法出栈，本身入栈
                if(stackByArray.peek() != null && ('*' == stackByArray.peek() || '/' == stackByArray.peek())){
                    Character c =stackByArray.pop();
                    rpn.add(String.valueOf(c));
                    System.out.println(c);
                }
                stackByArray.push(a);
            }else if(a == '+' || a=='-'){
                //加法减法时，操作符出栈，直到为空或遇到左括号
                while(true){
                    if(stackByArray.peek() == null || stackByArray.peek() == '('){
                        break;
                    }
                    Character c =stackByArray.pop();
                    rpn.add(String.valueOf(c));
                    System.out.println(c);
                }
                stackByArray.push(a);
            }
        }
        rpn.add(num);
        System.out.println(num);
        while (stackByArray.peek()!=null){
            Character c =stackByArray.pop();
            rpn.add(String.valueOf(c));
            System.out.println(c);
        }
        RPN(rpn.toArray(new String[rpn.size()]));
    }


    /**
     * 逆波兰表达式计算
     * @param express
     */
    private static void RPN(String[] express) {
        StackByArray<String> stackByArray = new StackByArray<>(10);
        Arrays.stream(express).forEach((e)->{
            String second,first;
            if(StringUtils.contains("+-*/",e)){
                second = stackByArray.pop();
                first = stackByArray.pop();
                switch (e){
                    case "+":
                        stackByArray.push((Integer.valueOf(first)+Integer.valueOf(second))+"");
                        break;
                    case "-":
                        stackByArray.push((Integer.valueOf(first)-Integer.valueOf(second))+"");
                        break;
                    case "*":
                        stackByArray.push((Integer.valueOf(first)*Integer.valueOf(second))+"");
                        break;
                    case "/":
                        stackByArray.push((Integer.valueOf(first)/Integer.valueOf(second))+"");
                        break;
                }
            }else{
                stackByArray.push(e);
            }

        });
        System.out.println(stackByArray.pop());
    }
}
