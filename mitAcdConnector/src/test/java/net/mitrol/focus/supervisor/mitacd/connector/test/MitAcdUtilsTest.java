package net.mitrol.focus.supervisor.mitacd.connector.test;

import net.mitrol.focus.supervisor.models.util.MitAcdUtils;
import org.junit.Assert;
import org.junit.Test;

public class MitAcdUtilsTest {

    @Test
    public void strToIntSin0Test() {
        Assert.assertArrayEquals(new Integer[]{0, 1}, MitAcdUtils.StrToIntSin0(";1;;;;;;;"));
    }

    @Test
    public void strToIntSin0NullTest() {
        Assert.assertArrayEquals(new Integer[]{}, MitAcdUtils.StrToIntSin0(null));
    }

    @Test
    public void strToIntSin0EmptyTest() {
        Assert.assertArrayEquals(new Integer[]{}, MitAcdUtils.StrToIntSin0(""));
    }

    @Test
    public void intToStrSin0Test() {
        Assert.assertEquals(";1", MitAcdUtils.IntToStrSin0(new Integer[]{0, 1, 0, 0, 0, 0, 0, 0}));
    }

    @Test
    public void intToStrSin0NullTest() {
        Assert.assertNull(MitAcdUtils.IntToStrSin0(null));
    }

    @Test
    public void intToStrSin0EmptyTest() {
        Assert.assertEquals("", MitAcdUtils.IntToStrSin0(new Integer[]{}));
    }
}
