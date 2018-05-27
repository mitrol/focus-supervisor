package net.mitrol.focus.supervisor.mitacd.connector.test;

import net.mitrol.utils.entities.ParameterBag;
import net.mitrol.utils.entities.SockMessage;
import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

public class SockMessageTest {

    @Test
    public void parserTest() throws UnsupportedEncodingException {
        String s = "cmd=1321&id=01225&st=07&Tce=20002&Ext=8099&IP=192.168.40.99&ata=%3B%3B%3B%3B%3B%3B%3B1726%3B%3B%3B%3B%3B%3B%3B%3B%3B%3B%3B%3B1726&isa=&iea=&";
        ParameterBag sockMessage = SockMessage.parse(s);
        Assert.assertEquals("1321", sockMessage.getString("cmd"));
        Assert.assertEquals("01225", sockMessage.getString("id"));
        Assert.assertEquals("07", sockMessage.getString("st"));
        Assert.assertEquals("20002", sockMessage.getString("Tce"));
        Assert.assertEquals("8099", sockMessage.getString("Ext"));
        Assert.assertEquals("192.168.40.99", sockMessage.getString("IP"));
        Assert.assertEquals(";;;;;;;1726;;;;;;;;;;;;1726", sockMessage.getString("ata"));
        Assert.assertEquals("", sockMessage.getString("isa"));
        Assert.assertEquals("", sockMessage.getString("iea"));
    }
}
