package org.sdv.proyectoviajes.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Stereotype;
import jakarta.inject.Named;
import org.sdv.proyectoviajes.interceptores.TransaccionJdbc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Stereotype
@ApplicationScoped
@Named
@TransaccionJdbc
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AServicio {
}
