package org.nocoder.djms.model;

/**
 * 字典表
 *
 * @author pufangfei@163.com
 * @ClassName: Dict
 * @date 2017年11月23日 下午9:50:58
 */
public class Dict {
    /**
     * 父节点
     */
    private String pid;
    /**
     * 名称
     */
    private String name;
    /**
     * 代码 30101004
     */
    private String code;

    public String getPid() {
        return pid;
    }

    public String getLabel() {
        return getName();
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pid=").append(pid);
        sb.append(", name=").append(name);
        sb.append(", code=").append(code);
        sb.append("]");
        return sb.toString();
    }
}