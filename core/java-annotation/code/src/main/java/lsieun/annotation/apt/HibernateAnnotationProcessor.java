package lsieun.annotation.apt;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes({"lsieun.annotation.apt.Persistent", "lsieun.annotation.apt.Id", "lsieun.annotation.apt.Property"})
public class HibernateAnnotationProcessor extends AbstractProcessor{

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        PrintStream ps = null;

        try {
            for (Element t: roundEnv.getElementsAnnotatedWith(Persistent.class)){
                Name clazzName = t.getSimpleName();

                Persistent per = t.getAnnotation(Persistent.class);
                //ps = new PrintStream(new FileOutputStream(clazzName + ".hbm.xml"));
                ps = System.out;
                ps.println("<?xml version=\"1.0\"?>");
                ps.println("<hibernate-mapping>");
                ps.print("<class name=\"" + t + "\" ");
                ps.println("table=\"" + per.table()  + "\">");
                for(Element f : t.getEnclosedElements()) {
                    if (f.getKind() == ElementKind.FIELD) {
                        Id id = f.getAnnotation(Id.class);
                        if (id != null) {
                            ps.println("  <id name=\"" + f.getSimpleName() + "\" " + 
                                    "column=\"" + id.column() + "\" " + 
                                    "type=\"" + id.type() + "\">");
                            ps.println("    <generator class=\"" + id.generator() + "\"/>");
                            ps.println("  </id>");
                        }

                        Property p = f.getAnnotation(Property.class);
                        if(p != null) {
                            ps.println("  <property name=\"" + f.getSimpleName() + "\" " + 
                                    "column=\"" + p.column() + "\" " + 
                                    "type=\"" + p.type() + "\"/>");
                        }
                    }
                }
                ps.println("</class>");
                ps.println("</hibernate-mapping>");
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        finally {
            if(ps != null) {
                try {
                    ps.close();
                }
                catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return true;
    }
}
