# Annotations

<!-- TOC -->

- [<ol>
<li>Annotations as special interfaces</li>
</ol>](#ol-liannotations-as-special-interfacesli-ol)

<!-- /TOC -->

从JDK5开始，Java增加了元数据（MetaData）的支持，也就是Annotation（注释）。

## 1. Annotations as special interfaces

As we mentioned before, **annotations are the syntactic sugar used to associate the metadata with different elements of Java language**.

Annotations by themselves do not have any direct effect on the element they are annotating. However, depending on the annotations and the way they are defined, they may be used by Java compiler (the great example of that is the `@Override`), by **annotation processors** and by **the code at runtime using reflection and other introspection techniques**.






