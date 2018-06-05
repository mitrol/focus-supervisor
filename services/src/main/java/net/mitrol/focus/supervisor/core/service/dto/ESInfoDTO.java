package net.mitrol.focus.supervisor.core.service.dto;

/**
 * @author ladassus
 */
public class ESInfoDTO {

    String name;
    String cluster_name;
    String cluster_uuid;
    ESVersion version;

    public ESVersion getVersion() {
        return version;
    }

    public void setVersion(ESVersion version) {
        this.version = version;
    }

    public String getName() {
        return name;

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCluster_name() {
        return cluster_name;
    }

    public void setCluster_name(String cluster_name) {
        this.cluster_name = cluster_name;
    }

    public String getCluster_uuid() {
        return cluster_uuid;
    }

    public void setCluster_uuid(String cluster_uuid) {
        this.cluster_uuid = cluster_uuid;
    }

    private static class  ESVersion {
        String number;
        String build_date;
        String build_hash;
        String build_snapshot;
        String lucene_version;
        String minimum_wire_compatibility_version;
        String minimum_index_compatibility_version;

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getBuild_date() {
            return build_date;
        }

        public void setBuild_date(String build_date) {
            this.build_date = build_date;
        }

        public String getBuild_hash() {
            return build_hash;
        }

        public void setBuild_hash(String build_hash) {
            this.build_hash = build_hash;
        }

        public String getBuild_snapshot() {
            return build_snapshot;
        }

        public void setBuild_snapshot(String build_snapshot) {
            this.build_snapshot = build_snapshot;
        }

        public String getLucene_version() {
            return lucene_version;
        }

        public void setLucene_version(String lucene_version) {
            this.lucene_version = lucene_version;
        }

        public String getMinimum_wire_compatibility_version() {
            return minimum_wire_compatibility_version;
        }

        public void setMinimum_wire_compatibility_version(String minimum_wire_compatibility_version) {
            this.minimum_wire_compatibility_version = minimum_wire_compatibility_version;
        }

        public String getMinimum_index_compatibility_version() {
            return minimum_index_compatibility_version;
        }

        public void setMinimum_index_compatibility_version(String minimum_index_compatibility_version) {
            this.minimum_index_compatibility_version = minimum_index_compatibility_version;
        }
    }
}
