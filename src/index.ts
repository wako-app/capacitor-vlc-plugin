import { registerPlugin } from '@capacitor/core';

import type { VlcPlayerPlugin } from './definitions';

const VlcPlayer = registerPlugin<VlcPlayerPlugin>('VlcPlayer', {
  web: () => import('./web').then(m => new m.VlcPlayerWeb()),
});

export * from './definitions';
export { VlcPlayer };
