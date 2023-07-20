package net.dahliasolutions.services;

import net.dahliasolutions.models.WikiTag;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface WikiTagServiceInterface {

    WikiTag createWikiTag(WikiTag wikiTag);
    Optional<WikiTag> findById(BigInteger id);
    Optional<WikiTag> findByName(String name);
    List<WikiTag> findAll();
    void save(WikiTag wikiTag);
    void deleteById(BigInteger id);
    Integer countReferences(BigInteger id);
}
