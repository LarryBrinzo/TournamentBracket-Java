package com.tournamentbracketjava.Contracts;

import com.tournamentbracketjava.Models.Annotations;
import com.tournamentbracketjava.Models.Connections;
import com.tournamentbracketjava.Models.Rows;
import java.util.List;

public interface BracketInterface {

     interface View {
         void setRows();
         void setAnnotations();
         void setConnections();
         void setWinningTeam();
    }

     interface Model {
         List<Rows> getRows();
         List<Annotations> getAnnotations();
         List<Connections> getConnections();
         int getWinningTeam();
    }
}
