package dev.wither.chunkcloner.cmdapi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandData {

    String name();

    String permission();

    boolean console() default false;
    boolean enabled() default true;

    String disabledMessage() default "&cThe command is currently disabled.";
    String permissionMessage() default "&cYou can't execute that command.";
    String consoleMessage() default "&cThe command can not be run by console.";

}
