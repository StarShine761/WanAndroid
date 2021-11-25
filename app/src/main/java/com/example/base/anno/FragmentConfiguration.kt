package com.example.base.anno

@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS)
annotation class FragmentConfiguration(

        val useEventBus: Boolean = false
)