package it.unibo.pixArt.model.project;

import java.util.LinkedList;
import java.util.List;

import it.unibo.pixArt.model.grid.PixelGrid;
import it.unibo.pixArt.model.grid.PixelMatrix;

public final class ProjectBuilderImpl implements ProjectBuilder {
    private String projectName;
    private String path;
    private String fileType;
    private List<PixelGrid> frames;

    @Override
    public ProjectBuilder projectName(final String projectName) {
        this.projectName = projectName;
        return this;
    }
    @Override
    public ProjectBuilder path(final String path) {
        this.path = path;
        return this;
    }
    @Override
    public ProjectBuilder fileType(final String fileType) {
        this.fileType = fileType;
        return this;
    }
    @Override
    public ProjectBuilder frames(final int size) {
        this.frames = new LinkedList<>();
        /*Build the first first grid */
        this.frames.add(0, new PixelMatrix.MatrixBuilder().setColumns(size).setRows(size).build());
        return this;
    }
    @Override
    public Project build() {
        return new ProjectImpl(this.projectName,this.path, this.fileType, this.frames);
    }

    
}
