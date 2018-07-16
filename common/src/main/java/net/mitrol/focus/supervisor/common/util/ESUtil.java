package net.mitrol.focus.supervisor.common.util;

import net.mitrol.utils.DateTimeUtils;

import java.time.Instant;

/** Utility class to Elasticsearch.
 *
 * @author ladassus
 *
 */
public class ESUtil {

    public static String getESIndexNameDateValue (String name){
        String now = DateTimeUtils.getStringFromInstant(Instant.now(), DateTimeUtils.MITROL_DATE_FORMAT);
        return name + "-" + now.replaceAll("/", ".");
    }

    public static String getESDateNowValue() {
        return DateTimeUtils.getStringFromInstant(Instant.now(), DateTimeUtils.MITROL_DATE_HOUR_FORMAT);
    }

}
