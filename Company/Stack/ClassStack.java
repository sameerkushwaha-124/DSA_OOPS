package Company.Stack;

import java.util.EmptyStackException;

public class ClassStack<T> {
    T arr[];
    int top = -1;
    int size;

    public ClassStack(int n){
        arr = (T[]) new Object[n]; // Object type ka array bana ke T type pe typecast kiya hain.
        size = n;
    }

    public void push(T val){
        if(top < size-1)
        arr[++top] = val;
    }

    public T peek(){
        if(top == -1){

        }
        return arr[top];
    }

    public T pop(){
        if(top == -1){
            throw new EmptyStackException();
        }
        return arr[top--];
    }

    public int length(){
        return top + 1;
    }

    public static void main(String[] args) {
        ClassStack<Integer> st = new ClassStack<Integer>(5);
        st.push(1);
        st.push(2);
        st.pop();


//        st.pop();

        System.out.println(st.peek());
        System.out.println(st.length());
    }
}
