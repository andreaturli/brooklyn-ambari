/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package io.brooklyn.ambari;

import javax.annotation.Nonnull;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;

/**
 * Basic object that bind a {@link Function} to a listener.
 *
 * @param <T> the listener type.
 */
public class FunctionRunningCallable<T> implements Runnable {

    final T listener;
    final Function<T, ?> function;

    public FunctionRunningCallable(@Nonnull T listener, @Nonnull Function<T, ?> function) {
        this.listener = Preconditions.checkNotNull(listener, "Parameter listener must not be null");
        this.function = Preconditions.checkNotNull(function, "Parameter function must not be null");
    }

    @Override
    public void run() {
        function.apply(listener);
    }
}