import { WebPlugin } from '@capacitor/core';

import type { VlcPlayerPlugin } from './definitions';

export class VlcPlayerWeb extends WebPlugin implements VlcPlayerPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
