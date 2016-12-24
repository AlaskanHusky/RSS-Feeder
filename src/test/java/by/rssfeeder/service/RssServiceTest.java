package by.rssfeeder.service;

import by.rssfeeder.entity.Item;
import by.rssfeeder.exception.RssException;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class RssServiceTest extends Object {

    private RssService rssService;

    @org.junit.Before
    public void setUp() throws Exception {
        rssService = new RssService();
    }

    @Test
    public void getItems() throws RssException {
        List<Item> items = rssService.getItems(new File("test-rss/jv.xml"));
        assertEquals(10, items.size());
        Item item = items.get(0);
        assertEquals("How to solve Source not found error during debug in Eclipse", item.getTitle());
    }

    @org.junit.Test
    public void getItems1() throws Exception {

    }
}