package com.kaiy.jvm.classload;


import sun.misc.Launcher;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;

public class ClassLoadTest {

    public static void main(String[] args) throws Exception {


        ClassLoader myClassLoad = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
//                    String fileName = "ClassLoadTest";
                    String fileName = name.substring(name.lastIndexOf(".") + 1);

                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];

                    is.read(b);
                    return defineClass(fileName, b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };

        Class<?> myClass = myClassLoad.loadClass(ClassLoadTest.class.getName());
        Object myObject = myClass.getDeclaredConstructor().newInstance();

        System.out.println(myObject.getClass().getName());
        System.out.println(myObject.hashCode());


        Class<?> aClass = ClassLoadTest.class.getClassLoader().loadClass(ClassLoadTest.class.getName());
        Object aObject = aClass.getDeclaredConstructor().newInstance();
        System.out.println(aObject.getClass().getName());
        System.out.println(aObject.hashCode());

        System.out.println(myObject instanceof ClassLoadTest);
        System.out.println(aObject instanceof ClassLoadTest);
        System.out.println();

        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        Arrays.asList(urLs).forEach(System.out::println);
        System.out.println();

        System.out.println(System.getProperty("java.class.path"));
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);

    }
}
