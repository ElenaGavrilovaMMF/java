package library.validator;

import java.util.List;

public interface Validator<T> {

    List<String> validate(T object);
}
