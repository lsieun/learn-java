# Java Annotations


## Serialization

**Serialization** is what you do to an instance of an object if you want to dump it to a raw buffer, save it to disk, transport it in a binary stream (e.g., sending an object over a network socket), or otherwise create a serialized binary representation of an object.

If you have no intention of serializing your class, you can add the annotation just above your class `@SuppressWarnings("serial")`.


