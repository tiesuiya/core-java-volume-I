package chapter08;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: tsy
 * @Date: 2021/1/2
 * @Description p357, 8-4 使用泛型反射API打印给定类有关内容
 */
public class GenericReflectionTest {
    public static void main(String[] args) {
//        String name = null;
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("请输入类名（如：java.util.Collections）：");
//        name = scanner.next();
//
//        print(name);

//        print("java.util.HashMap");
        print("java.lang.Object");
    }

    private static void print(String name) {
        try {
            Class<?> cl = Class.forName(name);
            printClass(cl);
            for (Method m : cl.getMethods()) {
                printMethod(m);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void printMethod(Method m) {
        String name = m.getName();
        System.out.print(Modifier.toString(m.getModifiers()));
        System.out.print(" ");
        printTypes(m.getTypeParameters(), "<", ",", ">", true);

        printType(m.getGenericReturnType(), false);
        System.out.print(" ");
        System.out.print(name);
        System.out.print("(");
        printTypes(m.getParameterTypes(), "", ", ", "", false);
        System.out.println(")");
    }

    private static void printClass(Class<?> cl) {
        System.out.print(cl);
        printTypes(cl.getTypeParameters(), "<", "，", ">", true);
        Type sc = cl.getGenericSuperclass();
        if (sc != null) {
            System.out.print(" extends ");
            printType(sc, false);
        }
        printTypes(cl.getGenericInterfaces(), " implements ", ", ", "", false);
        System.out.println();
    }

    private static void printTypes(Type[] types, String pre, String sep, String suf, boolean isDefinition) {
        if (pre.equals(" extends ") && Arrays.equals(types, new Type[]{Object.class})) {
            return;
        }
        if (types.length > 0) {
            System.out.print(pre);
        }
        for (int i = 0; i < types.length; i++) {
            if (i > 0) {
                System.out.print(sep);
            }
            printType(types[i], isDefinition);
        }
        if (types.length > 0) {
            System.out.print(suf);
        }
    }

    private static void printType(Type type, boolean isDefinition) {
        if (type instanceof Class) {
            Class t = (Class) type;
            System.out.print(t.getName());
        } else if (type instanceof TypeVariable) {
            TypeVariable t = (TypeVariable) type;
            System.out.print(t.getName());
            if (isDefinition) {
                printTypes(t.getBounds(), " extends ", " & ", "", false);
            }
        } else if (type instanceof WildcardType) {
            WildcardType t = (WildcardType) type;
            System.out.print("?");
            printTypes(t.getUpperBounds(), " extends ", " & ", "", false);
            printTypes(t.getLowerBounds(), " super ", " & ", "", false);
        } else if (type instanceof ParameterizedType) {
            ParameterizedType t = (ParameterizedType) type;
            Type owner = t.getOwnerType();
            if (owner != null) {
                printType(owner, false);
                System.out.print(".");
            }
            printType(t.getRawType(), false);
            printTypes(t.getActualTypeArguments(), "<", ", ", ">", false);
        } else if (type instanceof GenericArrayType) {
            GenericArrayType t = (GenericArrayType) type;
            System.out.print("");
            printType(t.getGenericComponentType(), isDefinition);
            System.out.print("[]");
        }

    }

}
