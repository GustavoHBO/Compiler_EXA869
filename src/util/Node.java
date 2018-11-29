/* 
 * Copyright (C) 2018 Gustavo Henrique and Marcus Aldrey
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us:	Gustavo Henrique	-	ghboliveira@hotmail.com
 * 		Marcos Vinícius		-	marcosviniciuscl@outlook.com
 * 		Marcus Aldrey		-	marcusaldrey@gmail.com
 *
 * See the original project in: <https://github.com/GustavoHBO/Compiler_EXA869>.
 */
package util;

import java.util.Arrays;

/**
 *
 * @author Gustavo Henrique
 * @author Marcos Vinícius
 */
public class Node {

    private String[][] productions;
    private final String value;
    private String[] first;
    private String[] follow;

    public Node(String value) {
        this.value = value;
    }

    public Node(String value, String[][] productions) {
        this.value = value;
        this.productions = productions;
    }

    public void addFirst(String sFirst) {
        if (first == null) {
            first = new String[1];
            first[0] = sFirst;
        } else {
            for (String string : first) {
                if (string.equals(sFirst)) {
                    return;
                }
            }
            String[] sAux = Arrays.copyOf(first, first.length + 1);
            sAux[sAux.length - 1] = sFirst;
            first = sAux;
        }
    }

    public void addFirst(String[] sFirst) {
        if (first == null) {
            first = Arrays.copyOf(sFirst, sFirst.length);
        } else {
            for (String string : sFirst) {
                if (!firstContains(string)) {
                    addFirst(string);
                }
            }
        }
    }

    public void removeFirst(String string) {
        String strAux;
        if (first != null) {
            for (int i = 0; i < first.length; i++) {
                if (first[i].equals(string)) {
                    first[i] = first[first.length - 1];
                }
            }
            first = Arrays.copyOf(first, first.length - 1);
        }
    }

    public boolean firstContains(String str) {
        for (String string : first) {
            if (string.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public void addFollow(String sFollow) {
        if (follow == null) {
            follow = new String[1];
            follow[0] = sFollow;
        } else {
            for (String string : follow) {
                if (string.equals(sFollow)) {
                    return;
                }
            }
            String[] sAux = Arrays.copyOf(follow, follow.length + 1);
            sAux[sAux.length - 1] = sFollow;
            follow = sAux;
        }
    }

    public void addFollow(String[] sFollow) {
        if (follow == null) {
            follow = Arrays.copyOf(sFollow, sFollow.length);
        } else {
            for (String string : sFollow) {
                if (!followContains(string)) {
                    addFollow(string);
                }
            }
        }
    }

    public boolean followContains(String str) {
        if (follow == null) {
            return false;
        }
        for (String string : follow) {
            if (string.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public String getValue() {
        return value;
    }

    public String[][] getProductions() {
        return productions;
    }

    public void setProductions(String[][] production) {
        this.productions = production;
    }

    /**
     * @return the first
     */
    public String[] getFirst() {
        return first;
    }

    /**
     * @param first the first to set
     */
    public void setFirst(String[] first) {
        this.first = first;
    }

    /**
     * @return the follow
     */
    public String[] getFollow() {
        return follow;
    }

    /**
     * @param follow the follow to set
     */
    public void setFollow(String[] follow) {
        this.follow = follow;
    }
}
