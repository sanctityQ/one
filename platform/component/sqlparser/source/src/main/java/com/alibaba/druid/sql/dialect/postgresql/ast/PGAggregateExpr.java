/*
 * Copyright 1999-2011 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.druid.sql.dialect.postgresql.ast;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLAggregateExpr;
import com.alibaba.druid.sql.dialect.postgresql.ast.expr.PGAnalytic;
import com.alibaba.druid.sql.dialect.postgresql.visitor.PGASTVisitor;
import com.alibaba.druid.sql.visitor.SQLASTVisitor;

public class PGAggregateExpr extends SQLAggregateExpr implements PGSQLObject {

	private PGAnalytic    over;
	
	public PGAnalytic getOver() {
		return over;
	}

	public void setOver(PGAnalytic over) {
		this.over = over;
	}

	private static final long serialVersionUID = 1L;

	public PGAggregateExpr(String methodName, Option option) {
		super(methodName, option);
	}

	public PGAggregateExpr(String methodName) {
		super(methodName);
	}
	
    public void output(StringBuffer buf) {
        buf.append(this.methodName);
        buf.append("(");
        int i = 0;
        for (int size = this.arguments.size(); i < size; ++i) {
            ((SQLExpr) this.arguments.get(i)).output(buf);
        }
        buf.append(")");

        if (this.over != null) {
            buf.append(" ");
            this.over.output(buf);
        }
    }


    protected void accept0(SQLASTVisitor visitor) {
        if (visitor instanceof PGASTVisitor) {
            this.accept0((PGASTVisitor) visitor);
        } else {
            if (visitor.visit(this)) {
                acceptChild(visitor, this.arguments);
                acceptChild(visitor, this.over);
            }
            visitor.endVisit(this);
        }
    }

    public void accept0(PGASTVisitor visitor) {
    	if (visitor.visit(this)) {
    		acceptChild(visitor, this.arguments);
    		acceptChild(visitor, this.over);
    	}
    	visitor.endVisit(this);
    }
}
