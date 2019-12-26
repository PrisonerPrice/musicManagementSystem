package com.prisonerprice.jdbc;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AlbumDAOTest.class,
        ArtistDAOTest.class,
        StockDAOTest.class
})
public class TestAll {
}
