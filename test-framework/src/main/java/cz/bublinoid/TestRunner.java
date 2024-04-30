package cz.bublinoid;

import cz.bublinoid.annotations.AfterSuite;
import cz.bublinoid.annotations.BeforeSuite;
import cz.bublinoid.annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class TestRunner {

    public static void run(Class<?> testClass) {
        try {
            Object instance = testClass.getDeclaredConstructor().newInstance();
            List<Method> beforeSuiteMethods = findAnnotatedMethods(testClass, BeforeSuite.class);
            List<Method> testMethods = findAnnotatedMethods(testClass, Test.class);
            List<Method> afterSuiteMethods = findAnnotatedMethods(testClass, AfterSuite.class);

            if (beforeSuiteMethods.size() > 1 || afterSuiteMethods.size() > 1) {
                throw new RuntimeException("Only one method allowed for @BeforeSuite and @AfterSuite annotations");
            }

            if (!beforeSuiteMethods.isEmpty()) {
                beforeSuiteMethods.get(0).invoke(instance);
            }

            testMethods.sort(Comparator.comparingInt(m -> {
                int priority = m.getAnnotation(Test.class).priority();
                if (priority < 1 || priority > 10) {
                    throw new IllegalArgumentException("Priority must be between 1 and 10");
                }
                return priority;
            }));

            int successful = 0;
            int failed = 0;

            for (Method testMethod : testMethods) {
                try {
                    testMethod.invoke(instance);
                    successful++;
                } catch (InvocationTargetException e) {
                    System.out.println("Test method " + testMethod.getName() + " failed: " + e.getCause());
                    failed++;
                }
            }

            if (!afterSuiteMethods.isEmpty()) {
                afterSuiteMethods.get(0).invoke(instance);
            }

            System.out.println("Tests run: " + (successful + failed));
            System.out.println("Successful: " + successful);
            System.out.println("Failed: " + failed);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static List<Method> findAnnotatedMethods(Class<?> clazz, Class<? extends Annotation> annotation) {
        List<Method> annotatedMethods = new ArrayList<>();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(annotation)) {
                annotatedMethods.add(method);
            }
        }
        return annotatedMethods;
    }
}
