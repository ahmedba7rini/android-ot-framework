package com.oneteam.framework.android.app.config;

import java.util.concurrent.ThreadFactory;

/**
 * Configuration class contains common configs options for app like:
 * <ul>
 * <li>Enable logging</li>
 * <li>Set the logger</li>
 * <li>Set memory caching size</li>
 * <li>Set disk caching size</li>
 * <li>Set background tasks priority</li>
 * </ul>
 * 
 * @author islamsamak
 * 
 */
public final class Configuration {

	public final int threadPoolSize;

	public final int threadPriority;

	public final int memoryCacheSize;

	public final int storageCacheSize;

	public final boolean isMonitored;

	public final ThreadFactory displayThreadFactory;

	public Configuration(final Builder builder) {

		threadPoolSize = builder.mThreadPoolSize;

		threadPriority = builder.mThreadPriority;

		memoryCacheSize = builder.mMemoryCacheSize;

		storageCacheSize = builder.mStorageCacheSize;

		isMonitored = builder.mIsMonitored;

		displayThreadFactory = new ThreadFactory() {

			@Override
			public Thread newThread(Runnable r) {

				Thread t = new Thread(r);

				t.setPriority(builder.mThreadPriority);

				return t;
			}
		};
	}

	public static class Builder {

		public static final int DEFAULT_THREAD_POOL_SIZE = 3;

		public static final int DEFAULT_THREAD_PRIORITY = Thread.NORM_PRIORITY - 1;

		// Default memory cache size is 5MB
		public static final int DEFAULT_MEMORY_CACHE_SIZE = 5 * 1024 * 1024;

		// Default external storage cache size is 50MB
		public static final int DEFAULT_STORAGE_CACHE_SIZE = 50 * 1024 * 1024;

		private int mThreadPoolSize = DEFAULT_THREAD_POOL_SIZE;

		private int mThreadPriority = DEFAULT_THREAD_PRIORITY;

		private int mMemoryCacheSize;

		private int mStorageCacheSize;

		private boolean mIsMonitored;

		public Builder setMemoryCacheSize(int size) {

			mMemoryCacheSize = size;

			return this;
		}

		public Builder setStorageCacheSize(int size) {

			mStorageCacheSize = size;

			return this;
		}

		public Builder enableMonitoring() {

			mIsMonitored = true;

			return this;
		}

		public Configuration build() {

			Configuration configs = new Configuration(this);

			return configs;
		}

	}

}
