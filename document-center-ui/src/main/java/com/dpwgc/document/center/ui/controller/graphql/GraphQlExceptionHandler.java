package com.dpwgc.document.center.ui.controller.graphql;

import com.dpwgc.document.center.infrastructure.exception.InnerException;
import com.dpwgc.document.center.infrastructure.util.ExceptionUtil;
import com.dpwgc.document.center.infrastructure.util.LogUtil;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;

@Component
public class GraphQlExceptionHandler extends DataFetcherExceptionResolverAdapter {
    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {

        LogUtil.info("graphql error", ExceptionUtil.GetStackTrace((Exception) ex),"GraphQlExceptionHandler");

        String errorMessage = "SYSTEM ERROR";

        if (ex instanceof InnerException) {
            errorMessage = ex.getMessage();
        }

        return GraphqlErrorBuilder.newError()
                .errorType(ErrorType.BAD_REQUEST)
                .message(errorMessage)
                .path(env.getExecutionStepInfo().getPath())
                .location(env.getField().getSourceLocation())
                .build();
    }
}