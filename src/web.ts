import { WebPlugin } from '@capacitor/core';

import type { VlcPlayerPlugin } from './definitions';

export class VlcPlayerWeb extends WebPlugin implements VlcPlayerPlugin {
  async stream(options: { link: string }): Promise<{ link: string }> {
      console.log('channels: ', options.link);
      return options;
  }
}
