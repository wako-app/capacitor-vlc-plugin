import { WebPlugin } from '@capacitor/core';

import type { VlcPlayerPlugin } from './definitions';

export class VlcPlayerWeb extends WebPlugin implements VlcPlayerPlugin {
  async stream(options: { channels: any[]; idx: number; }): Promise<{ channels: any[]; idx: number; }> {
      console.log('channels: ', options.channels);
      console.log('index: ', options.idx);
      return options;
  }
}
