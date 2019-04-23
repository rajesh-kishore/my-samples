/**
 * This code is free software; you can redistribute it and/or modify it.
 */

package com.kishore.shared;

/**
 * @author Rajesh Kishore
 * @version 1.0
 * @since V1
 */
public final class SharedLock {

  private int index;

  private volatile boolean shutDownInitiated = false;

  /**
   * @return the index
   */
  public final int getIndex() {
    return index;
  }

  public final void incrementIndex(int numbers[]) {
    this.index++;
    if ( this.index >= numbers.length ) {
      setShutDownInitiated();
    }
  }

  /**
   * @return the shutDownInitiated
   */
  public final boolean isShutDownInitiated() {
    return shutDownInitiated;
  }

  /**
   * 
   */
  public final void setShutDownInitiated() {
    this.shutDownInitiated = true;
  }

}
