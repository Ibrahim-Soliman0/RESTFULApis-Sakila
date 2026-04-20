package repository.impl;

import entity.Language;
import repository.LanguageRepository;
import repository.impl.genaric.BaseRepositoryImpl;

public class LanguageRepositoryImpl extends BaseRepositoryImpl<Language, Short>
        implements LanguageRepository {

    public LanguageRepositoryImpl() {
        super(Language.class);
    }
}